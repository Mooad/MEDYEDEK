import { Injectable } from '@angular/core';
import { ProfileDto } from '../entities/UserResetDto';

@Injectable()
export class SharedProfile {

   public profile :ProfileDto;
}