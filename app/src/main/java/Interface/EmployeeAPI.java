package Interface;

import java.util.List;

import Model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getEmployee();
}
