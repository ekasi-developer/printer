import { registerPlugin } from '@capacitor/core';

import { Printer as Interface } from './definitions';

const Printer = registerPlugin<Interface>('Printer', {
  web: () => import('./web').then(m => new m.PrinterWeb()),
});

export * from './definitions';
export { Printer };
