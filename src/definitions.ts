export interface PrinterPlugin {
  print(options: { transaction: any, base64: string }): Promise<{ success: boolean, message: string }>;
}
