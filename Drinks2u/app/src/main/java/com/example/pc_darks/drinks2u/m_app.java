package com.example.pc_darks.drinks2u;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class m_app extends AppCompatActivity {

    private TextView correo;
    private TextView contrasena;
    private EditText correo1;
    private EditText contrasena1;
    private TextView loginErrorMsg;
    private Button entrar;

    ArrayList<Usuario> access = new ArrayList<Usuario>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1){
            String name = data.getStringExtra("name");
            String lastname = data.getStringExtra("lastname");
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            String cellphone = data.getStringExtra("cellphone");

            Usuario log = new Usuario(name, lastname, email, password, cellphone);
            access.add(log);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_app);

        correo1 = (EditText) findViewById(R.id.txtedit1);
        contrasena1 = (EditText) findViewById(R.id.txtedit2);
        loginErrorMsg = (TextView) findViewById(R.id.txtview3);

        Usuario datos1 = new Usuario("ayrancan619@gmail.com","sahc","Santiago","Huh","9841390549");
        Usuario datos2 = new Usuario("alexis_fdz@gmail.com","alex","Alexis","Fernandez","9841300549");
        Usuario datos3 = new Usuario("balam_19@gmail.com","aleb","Alejandro","Balam","9841290339");
        Usuario datos4 = new Usuario("alexsis_9@gmail.com","aleji","Alexsis","Jimenez","9841190749");

        access.add(datos1);
        access.add(datos2);
        access.add(datos3);
        access.add(datos4);

        controlador();

        entrar = (Button) findViewById(R.id.btnlogin);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Usuario usuario: access) {
                    if (usuario.getCorreo().equals(correo1.getText().toString()) && usuario.getContrasena().equals(contrasena1.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Bienvenido Usuario " + usuario.getNombre(), Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void controlador(){
        Button entry = (Button) findViewById(R.id.btnregistro);

        entry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), register.class);
                /*startActivity(register);*/
                startActivityForResult(register, 1);
            }
        });
    }

}
