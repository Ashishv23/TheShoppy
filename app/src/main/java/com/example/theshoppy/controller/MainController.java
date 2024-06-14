package com.example.theshoppy.controller;

import android.view.View;
import android.widget.TextView;

import com.example.theshoppy.R;
import com.example.theshoppy.model.Computer;
import com.example.theshoppy.model.Peripherals;
import com.example.theshoppy.view.MainActivity;
import java.util.Arrays;

import java.text.DecimalFormat;

public class MainController {
    private MainActivity view;

    public MainController(MainActivity view) {
        this.view = view;
    }

    public void calculateInvoice() {
        String customerName = view.getCustomerName().getText().toString();
        String province = view.getProvince().getText().toString();
        int selectedComputerTypeId = view.getComputerType().getCheckedRadioButtonId();
        String brand = view.getBrandSpinner().getSelectedItem().toString();
        boolean hasSSD = view.getSsd().isChecked();
        boolean hasPrinter = view.getPrinter().isChecked();
        int selectedLaptopPeripheralsId = view.getLaptopPeripherals().getCheckedRadioButtonId();
        int selectedDesktopPeripheralsId = view.getDesktopPeripherals().getCheckedRadioButtonId();

        boolean valid = true;

        // Clear previous error messages
        clearErrors();

        // Check fields are not empty
        if (customerName.isEmpty()) {
            setError(view.getCustomerNameError(), "Name is required");
            valid = false;
        }

        if (province.isEmpty() || !isValidProvince(province)) {
            setError(view.getProvinceError(), "Province is required");
            valid = false;
        }

        if (selectedComputerTypeId == -1) {
            setError(view.getComputerTypeError(), "Please select a computer type.");
            valid = false;
        }

        if (brand.equals("Select a brand")) {
            setError(view.getBrandError(), "Please select a brand.");
            valid = false;
        }

        if (!hasSSD && !hasPrinter) {
            setError(view.getAdditionFeaturesError(), "Please select additional feature.");
            valid = false;
        }

        if (selectedComputerTypeId == R.id.laptop) {
            if (selectedLaptopPeripheralsId == -1) {
                setError(view.getLaptopPeripheralsError(), "Please select a laptop peripheral.");
                valid = false;
            }
        } else if (selectedComputerTypeId == R.id.desktop) {
            if (selectedDesktopPeripheralsId == -1) {
                setError(view.getDesktopPeripheralsError(), "Please select a desktop peripheral.");
                valid = false;
            }
        }

        if (!valid) {
            return;
        }

        Computer.Type type = selectedComputerTypeId == R.id.laptop ? Computer.Type.LAPTOP : Computer.Type.DESKTOP;
        double basePrice = Computer.getBasePrice(type, brand);
        Computer computer = new Computer(type, brand, basePrice);

        double cost = computer.getBasePrice();

        // Add additional costs
        StringBuilder additionalCosts = new StringBuilder();
        if (hasSSD) {
            cost += Peripherals.SSD_COST;
            additionalCosts.append("SSD, ");
        }
        if (hasPrinter) {
            cost += Peripherals.PRINTER_COST;
            additionalCosts.append("Printer, ");
        }

        // Add peripheral costs based on computer type
        if (type == Computer.Type.LAPTOP) {
            int laptopPeripheralId = view.getLaptopPeripherals().getCheckedRadioButtonId();
            cost += getLaptopPeripheralCost(laptopPeripheralId, additionalCosts);
        } else {
            int desktopPeripheralId = view.getDesktopPeripherals().getCheckedRadioButtonId();
            cost += getDesktopPeripheralCost(desktopPeripheralId, additionalCosts);
        }

        double tax = cost * 0.13;
        double totalCost = cost + tax;

        // Format cost to two decimal places
        DecimalFormat df = new DecimalFormat("#.00");

        // Create invoice text
        String invoiceText = String.format(
                "Customer: %s\nProvince: %s\nComputer: %s\nBrand: %s\nAdditional: %s\nCost: $%s",
                customerName,
                province,
                type == Computer.Type.LAPTOP ? "Laptop" : "Desktop",
                brand,
                additionalCosts.toString().isEmpty() ? "None" : additionalCosts.toString().replaceAll(", $", ""),
                df.format(totalCost)
        );

        view.getInvoiceOutput().setText(invoiceText);
    }

    // Clear errors function
    private void clearErrors() {
        setError(view.getCustomerNameError(), null);
        setError(view.getProvinceError(), null);
        setError(view.getComputerTypeError(), null);
        setError(view.getBrandError(), null);
        setError(view.getAdditionFeaturesError(), null);
        setError(view.getLaptopPeripheralsError(), null);
        setError(view.getDesktopPeripheralsError(), null);
    }

    private void setError(TextView errorTextView, String errorMessage) {
        errorTextView.setText(errorMessage);
        errorTextView.setVisibility(errorMessage == null ? View.GONE : View.VISIBLE);
    }

    // Validate if the province is in the predefined list
    private boolean isValidProvince(String province) {
        String[] provinces = view.getResources().getStringArray(R.array.provinces);
        return Arrays.asList(provinces).contains(province);
    }

    private double getLaptopPeripheralCost(int peripheralId, StringBuilder additionalCosts) {
        if (peripheralId == R.id.coolingMat) {
            additionalCosts.append("Cooling Mat, ");
            return Peripherals.COOLING_MAT_COST;
        } else if (peripheralId == R.id.usbHub) {
            additionalCosts.append("USB C-HUB, ");
            return Peripherals.USB_C_HUB_COST;
        } else if (peripheralId == R.id.laptopStand) {
            additionalCosts.append("Laptop Stand, ");
            return Peripherals.LAPTOP_STAND_COST;
        } else {
            return 0.0;
        }
    }

    private double getDesktopPeripheralCost(int peripheralId, StringBuilder additionalCosts) {
        if (peripheralId == R.id.webcam) {
            additionalCosts.append("Webcam, ");
            return Peripherals.WEBCAM_COST;
        } else if (peripheralId == R.id.externalHardDrive) {
            additionalCosts.append("External Hard Drive, ");
            return Peripherals.EXTERNAL_HARD_DRIVE_COST;
        } else {
            return 0.0;
        }
    }
}
