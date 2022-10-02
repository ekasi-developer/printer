import { WebPlugin } from '@capacitor/core';

import type { PrinterPlugin } from './definitions';

export class PrinterWeb extends WebPlugin implements PrinterPlugin {
  async print(options: { transaction: any, base64: string }): Promise<{ success: boolean, message: string }> {
    console.log('ECHO', options);
    return { success: true, message: 'Slip has been printed successfully.' };
  }
}
