import { WebPlugin } from '@capacitor/core';

import type { Printer, SuccessResponse, Transaction } from './definitions';

export class PrinterWeb extends WebPlugin implements Printer {
  async print(options: Transaction): Promise<SuccessResponse> {
    console.log('ECHO', options);
    return { message: 'Slip has been printed successfully.' };
  }
}
