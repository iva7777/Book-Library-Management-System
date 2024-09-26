import { ReaderCard } from "./reader-card";

export interface Reader {
    id: number;
    firstName: string;
    lastName: string;
    phone: string;
    address: string;
    email: string;
    readerCard?: ReaderCard;
    userId: number;
}
