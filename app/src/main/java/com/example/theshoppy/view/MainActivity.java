package com.example.theshoppy.view;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.theshoppy.R;
import com.example.theshoppy.controller.MainController;

public class MainActivity extends AppCompatActivity {
    private EditText customerName;
    private AutoCompleteTextView province;
    private RadioGroup computerType;
    private Spinner brandSpinner;
    private CheckBox ssd, printer;
    private RadioGroup laptopPeripherals, desktopPeripherals;
    private Button calculateButton;
    private TextView invoiceOutput;
    private MainController controller;

    // Error TextViews
    private TextView customerNameError, provinceError, computerTypeError, brandError, additionalFeaturesError, laptopPeripheralsError, desktopPeripheralsError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        customerName = findViewById(R.id.customerName);
        province = findViewById(R.id.province);
        computerType = findViewById(R.id.computerType);
        brandSpinner = findViewById(R.id.brand);
        ssd = findViewById(R.id.ssd);
        printer = findViewById(R.id.printer);
        laptopPeripherals = findViewById(R.id.laptopPeripherals);
        desktopPeripherals = findViewById(R.id.desktopPeripherals);
        calculateButton = findViewById(R.id.calculateButton);
        invoiceOutput = findViewById(R.id.invoiceOutput);

        // Initialize error text views
        customerNameError = findViewById(R.id.customerNameError);
        provinceError = findViewById(R.id.provinceError);
        computerTypeError = findViewById(R.id.computerTypeError);
        brandError = findViewById(R.id.brandError);
        additionalFeaturesError = findViewById(R.id.additionalFeaturesError);
        laptopPeripheralsError = findViewById(R.id.laptopPeripheralError);
        desktopPeripheralsError = findViewById(R.id.desktopPeripheralError);

        // Initialize controller
        controller = new MainController(this);

        // Set up AutoCompleteTextView for provinces
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.provinces));
        province.setAdapter(provinceAdapter);

        // Set up Spinner for brands
        ArrayAdapter<CharSequence> brandAdapter = ArrayAdapter.createFromResource(this,
                R.array.brands, android.R.layout.simple_spinner_item);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandAdapter);

        // Set up Calculate button click listener
        calculateButton.setOnClickListener(v -> controller.calculateInvoice());
    }

    public EditText getCustomerName() {
        return customerName;
    }

    public AutoCompleteTextView getProvince() {
        return province;
    }

    public RadioGroup getComputerType() {
        return computerType;
    }

    public Spinner getBrandSpinner() {
        return brandSpinner;
    }

    public CheckBox getSsd() {
        return ssd;
    }

    public CheckBox getPrinter() {
        return printer;
    }

    public RadioGroup getLaptopPeripherals() {
        return laptopPeripherals;
    }

    public RadioGroup getDesktopPeripherals() {
        return desktopPeripherals;
    }

    public TextView getInvoiceOutput() {
        return invoiceOutput;
    }

    public TextView getCustomerNameError() {
        return customerNameError;
    }

    public TextView getProvinceError() {
        return provinceError;
    }

    public TextView getComputerTypeError() {
        return computerTypeError;
    }

    public TextView getBrandError() {
        return brandError;
    }

    public  TextView getAdditionFeaturesError() {
        return additionalFeaturesError;
    }

    public TextView getLaptopPeripheralsError() {
        return laptopPeripheralsError;
    }

    public TextView getDesktopPeripheralsError() {
        return desktopPeripheralsError;
    }
}
