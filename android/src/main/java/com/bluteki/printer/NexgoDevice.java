package com.bluteki.printer;

import android.content.Context;
import android.graphics.Bitmap;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.nexgo.oaf.apiv3.APIProxy;
import com.nexgo.oaf.apiv3.DeviceEngine;
import com.nexgo.oaf.apiv3.SdkResult;
import com.nexgo.oaf.apiv3.device.led.LEDDriver;
import com.nexgo.oaf.apiv3.device.led.LightModeEnum;
import com.nexgo.oaf.apiv3.device.printer.AlignEnum;
import com.nexgo.oaf.apiv3.device.printer.Printer;

public class NexgoDevice {
    private final Printer printer;
    private final LEDDriver ledDriver;
    private final PluginCall call;

    /**
     *
     * @param pluginCall Plugin call object.
     */
    public NexgoDevice(Context context, PluginCall pluginCall) {
        DeviceEngine deviceEngine = APIProxy.getDeviceEngine(context);
        ledDriver = deviceEngine.getLEDDriver();
        printer = deviceEngine.getPrinter();
        call = pluginCall;
    }

    /**
     * Print transaction slip.
     *
     * @param transactionId Id of transaction.
     * @param amount Amount of transaction.
     * @param cellphoneNumber Customer cellphone number.
     * @param employee Employee name.
     */
    public void print(String transactionId, Double amount, String cellphoneNumber, String employee) {
        greenLedLight(true);
        printer.initPrinter();
        slip(transactionId, amount, cellphoneNumber, employee);
    }

    /**
     * Change Nexgo device green light LED.
     *
     * @param state Status of the LED light.
     */
    protected void greenLedLight(boolean state) {
        ledDriver.setLed(LightModeEnum.GREEN, state);
    }

//    protected Bitmap getImage() {
//
//    }

    /**
     *
     * @param transactionId Id of transaction.
     * @param amount Amount of transaction.
     * @param cellphoneNumber Customer cellphone number.
     * @param employee Employee name.
     */
    protected void slipContent(String transactionId, Double amount, String cellphoneNumber, String employee) {

        /***************************** Text Message **********************************/
        printer.appendPrnStr("Bluteki", 20, AlignEnum.LEFT, false);
        /**************************** Transaction ID *********************************/
        printer.appendPrnStr("Transaction ID", 30, AlignEnum.LEFT, false);
        printer.appendPrnStr(transactionId, 30, AlignEnum.RIGHT, false);
        /***************************  Employee Name  *********************************/
        printer.appendPrnStr("Employee", 30, AlignEnum.RIGHT, false);
        printer.appendPrnStr(employee, 30, AlignEnum.RIGHT, false);
        /*************************** Cellphone Number ********************************/
        printer.appendPrnStr("Cellphone No.", 30, AlignEnum.RIGHT, false);
        printer.appendPrnStr(cellphoneNumber, 30, AlignEnum.RIGHT, false);
        /**************************** Amount *********************************/
        printer.appendPrnStr("Amount", 40, AlignEnum.RIGHT, true);
        printer.appendPrnStr(String.valueOf(amount), 40, AlignEnum.RIGHT, true);
    }

    /**
     * Print transaction slip.
     *
     * @param transactionId Id of transaction.
     * @param amount Amount of transaction.
     * @param cellphoneNumber Customer cellphone number.
     * @param employee Employee name.
     */
    protected void slip(String transactionId, Double amount, String cellphoneNumber, String employee) {
        switch (printer.getStatus()){
            case SdkResult.Success:
                slipContent(transactionId, amount, cellphoneNumber, employee);
                slipPrint();
                break;
            case SdkResult.Printer_PaperLack:
                call.reject("Printer is out of paper.");
                break;
            default:
                call.reject("Something went wrong when trying to initialize printer.");
                break;
        }
    }

    /**
     *
     */
    protected void slipPrint() {
        printer.startPrint(true, state -> {
            JSObject json = new JSObject();
            switch (state) {
                case SdkResult.Success:
                    json.put("message", "Slip has been printed successfully.");
                    call.resolve(json);
                    break;
                case SdkResult.Printer_Busy:
                    call.reject("Printing device is current busy.");
                    break;
                default:
                    call.reject("Something went wrong please try again.");
                    break;
            }
            greenLedLight(false);
        });
    }
}
