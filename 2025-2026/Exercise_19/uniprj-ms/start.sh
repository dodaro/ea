#!/usr/bin/env bash
# Avvia tutti i servizi di uniprj-ms in ordine corretto.
# Uso: ./start.sh [--build]
#   --build  esegue mvn clean install prima di avviare

set -e

BASE="$(cd "$(dirname "$0")" && pwd)"
LOGS="$BASE/logs"
mkdir -p "$LOGS"

if [[ "$1" == "--build" ]]; then
  echo "[BUILD] mvn clean install..."
  mvn -f "$BASE/pom.xml" clean install -q
fi

stop_all() {
  echo "[STOP] killing services on ports 8060 8061 8088..."
  lsof -ti:8060,8061,8088 | xargs kill -9 2>/dev/null || true
}

wait_for() {
  local url="$1" label="$2"
  echo -n "[WAIT] $label..."
  until curl -s "$url" > /dev/null 2>&1; do
    sleep 2; echo -n "."
  done
  echo " OK"
}

stop_all

echo "[START] config-service"
nohup java -jar "$BASE/config-service/target/"*.jar > "$LOGS/config-service.log" 2>&1 &
wait_for "http://localhost:8088/discovery-service/default" "config-service"

echo "[START] discovery-service"
nohup java -jar "$BASE/discovery-service/target/"*.jar > "$LOGS/discovery-service.log" 2>&1 &
wait_for "http://localhost:8061/eureka/apps" "discovery-service"

echo "[START] student-service, teacher-service, course-service, gateway-service"
for svc in student-service teacher-service course-service gateway-service; do
  nohup java -jar "$BASE/$svc/target/"*.jar > "$LOGS/$svc.log" 2>&1 &
done

echo "[WAIT] servizi in Eureka..."
for svc in STUDENT-SERVICE TEACHER-SERVICE COURSE-SERVICE GATEWAY-SERVICE; do
  until curl -s http://localhost:8061/eureka/apps | grep -q "$svc"; do sleep 3; done
  echo "  $svc UP"
done

echo ""
echo "========================================"
echo " uniprj-ms avviato"
echo "  Gateway:   http://localhost:8060"
echo "  Eureka:    http://localhost:8061"
echo "  Config:    http://localhost:8088"
echo "  Logs:      $LOGS/"
echo ""
echo "  GET /student-api/students"
echo "  GET /teacher-api/teachers"
echo "  GET /teacher-api/teachers/1"
echo "  GET /course-api/courses-teachers"
echo "========================================"
