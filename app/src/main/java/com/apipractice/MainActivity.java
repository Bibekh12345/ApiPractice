package com.apipractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Interface.EmployeeAPI;
import Model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvdata;
    private final static String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvdata = findViewById(R.id.et_data);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<List<Employee>> listCall = employeeAPI.getEmployee();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful()){
                    tvdata.setText("Code: " + response.code());
                    return;
                }

                List<Employee> employeeList = response.body();
                for (Employee employee : employeeList){
                    String content = "";
                    content += "ID: "+ employee.getId()+"\n";
                    content += "Employee Name: "+ employee.getId()+"\n";
                    content += "Employee Salary: "+ employee.getId()+"\n";
                    content += "Employee Age: "+ employee.getId()+"\n";
                    content += "------------------------"+ "\n";

                    tvdata.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                tvdata.setText("Error" + t.getLocalizedMessage());
            }
        });
    }
}
