export interface Transaction {
  [key: string]: string;
}

export interface SuccessResponse {
  message: string;
}

export interface Printer {
  print(options: any): Promise<SuccessResponse>;
}
