export * from './courseController.service';
import { CourseControllerService } from './courseController.service';
export * from './othersController.service';
import { OthersControllerService } from './othersController.service';
export * from './studentController.service';
import { StudentControllerService } from './studentController.service';
export const APIS = [CourseControllerService, OthersControllerService, StudentControllerService];
