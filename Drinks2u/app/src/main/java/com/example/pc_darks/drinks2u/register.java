package com.example.pc_darks.drinks2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private EditText name;
    private EditText lastame;
    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private EditText cellphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.editres1);
        lastame = (EditText) findViewById(R.id.editres2);
        email = (EditText) findViewById(R.id.editres3);
        password = (EditText) findViewById(R.id.editres4);
        confirmpassword = (EditText) findViewById(R.id.editres5);
        cellphone = (EditText) findViewById(R.id.editres6);

        registrarse();
    }

    public void registrarse(){
        Button entry1 = (Button) findViewById(R.id.btnregister);

        entry1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password.getText().toString().equals(confirmpassword.getText().toString())){
                    Intent registro = new Intent();

                    registro.putExtra("name",name.getText().toString());
                    registro.putExtra("lastname",name.getText().toString());
                    registro.putExtra("email",name.getText().toString());
                    registro.putExtra("password",name.getText().toString());
                    registro.putExtra("cellphone",name.getText().toString());

                    setResult(1, registro);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
