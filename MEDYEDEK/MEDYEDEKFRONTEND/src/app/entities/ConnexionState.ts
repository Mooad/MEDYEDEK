import { Serializable } from './Post';

export interface ConnexionState extends Serializable {
    timestamp?: string;
    status?: string;
    error?: string;
    message?: string;
    path?: string;
}
