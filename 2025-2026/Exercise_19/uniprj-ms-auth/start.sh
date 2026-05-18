#!/usr/bin/env bash
# Avvia tutti i servizi di uniprj-ms-auth in ordine corretto.
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

echo "[START] config-service-auth"
nohup java -jar "$BASE/config-service-auth/target/"*.jar > "$LOGS/config-service-auth.log" 2>&1 &
wait_for "http://localhost:8088/auth-service/default" "config-service-auth"

echo "[START] discovery-service-auth"
nohup java -jar "$BASE/discovery-service-auth/target/"*.jar > "$LOGS/discovery-service-auth.log" 2>&1 &
wait_for "http://localhost:8061/eureka/apps" "discovery-service-auth"

echo "[START] auth-service, student-service, teacher-service, course-service, gateway-service"
for svc in auth-service-auth student-service-auth teacher-service-auth course-service-auth gateway-service-auth; do
  nohup java -jar "$BASE/$svc/target/"*.jar > "$LOGS/$svc.log" 2>&1 &
done

echo "[WAIT] servizi in Eureka..."
for svc in AUTH-SERVICE STUDENT-SERVICE TEACHER-SERVICE COURSE-SERVICE GATEWAY-SERVICE; do
  until curl -s http://localhost:8061/eureka/apps | grep -q "$svc"; do sleep 3; done
  echo "  $svc UP"
done

AUTH_PORT=$(curl -s http://localhost:8061/eureka/apps/AUTH-SERVICE | grep -o '<port enabled="true">[^<]*' | sed 's/<port enabled="true">//')

echo ""
echo "========================================"
echo " uniprj-ms-auth avviato"
echo "  Gateway:      http://localhost:8060"
echo "  Eureka:       http://localhost:8061"
echo "  Config:       http://localhost:8088"
echo "  Auth-service: http://localhost:$AUTH_PORT"
echo "  Logs:         $LOGS/"
echo ""
echo "  POST /auth/register  {\"email\":\"...\",\"password\":\"...\"}"
echo "  POST /auth/login     {\"email\":\"...\",\"password\":\"...\"}"
echo "  GET  /student-api/students          (no token)"
echo "  GET  /teacher-api/teachers          (Bearer token)"
echo "  GET  /course-api/courses-teachers   (Bearer token)"
echo "========================================"
