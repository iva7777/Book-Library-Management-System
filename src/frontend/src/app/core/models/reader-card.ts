export interface ReaderCard {
    id: number;
    readerId: number;
    readerNames: string;
    rentDate: Date;
    returnDate: Date;
}

export interface ApiResponse<T> {
    data: T;
    message: string;
    successful: boolean;
}
