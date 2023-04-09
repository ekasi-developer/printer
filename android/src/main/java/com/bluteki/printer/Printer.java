package com.bluteki.printer;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Printer")
public class Printer extends Plugin {
    @PluginMethod
    public void print(PluginCall call) {
        try {
            NexgoDevice nexgoDevice = new NexgoDevice(this.getContext(), call);
            String transactionId = call.getString("transactionId");
            Double amount = call.getDouble("amount");
            String cellphoneNumber = call.getString("cellphoneNumber");
            String employee = call.getString("employee");
            nexgoDevice.print(transactionId, amount, cellphoneNumber, employee);
        } catch (Exception exception) {
            call.reject(exception.getMessage());
        }
    }
}
