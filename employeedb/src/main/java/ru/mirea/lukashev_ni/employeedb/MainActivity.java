package ru.mirea.lukashev_ni.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView employeeNameTextView;
    private TextView employeeSalaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeNameTextView = findViewById(R.id.employeeNameTextView);
        employeeSalaryTextView = findViewById(R.id.employeeSalaryTextView);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "John Smith";
        employee.salary = 10000;
        // запись сотрудников в базу
        employeeDao.insert(employee);
        // Загрузка всех работников
        List<Employee> employees = employeeDao.getAll();
        // Получение определенного работника с id = 1
        employee = employeeDao.getById(1);
        // Обновление полей объекта
        employee.salary = 20000;
        employeeDao.update(employee);

        Log.d(TAG, employee.name + " " + employee.salary);

        employeeNameTextView.setText("Employee Name: " + employee.name);
        employeeSalaryTextView.setText("Employee Salary: " + employee.salary);

        Log.d(TAG, employee.name + " " + employee.salary);
    }
}