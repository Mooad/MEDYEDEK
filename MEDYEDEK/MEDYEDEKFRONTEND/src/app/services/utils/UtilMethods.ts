import {Injectable} from "@angular/core";

@Injectable()
export class Utils {


  getRandomString(length: number): string {
    const characters = 'ABCDEFGHIJyz0123456789';
    let result = '';
    const charactersLength = characters.length;

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * charactersLength);
      result += characters.charAt(randomIndex);
    }

    return result;
  }


    generateCustomUniqueId(): string {
    const timestampPart = new Date().getTime().toString(16);
    const randomPart = Math.floor(Math.random() * 10000).toString(16).padStart(4, '0');
    const uniqueId = timestampPart + randomPart;
    return uniqueId;
  }



}
