package com.example.buscamusico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nome,nascimento,endereço,telefone,rg,cpf,email,usuario,senha,genero,instrumento;
    Button inser,update,delete,view;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome= findViewById(R.id.edt_nome);
        nascimento= findViewById(R.id.edt_nascimentp);
        endereço= findViewById(R.id.edt_endereco);
        telefone= findViewById(R.id.edt_telefone);
        rg = findViewById(R.id.edt_rg);
        cpf = findViewById(R.id.edt_cpf);
        email= findViewById(R.id.edt_email);
        usuario= findViewById(R.id.edt_usuario);
        senha= findViewById(R.id.edt_senha);
        genero= findViewById(R.id.edt_genero);
        instrumento= findViewById(R.id.edt_instrumento);


        inser= findViewById(R.id.btn_Insert);
        update = findViewById(R.id.btn_Update);
        delete= findViewById(R.id.btn_Delete);
        view = findViewById(R.id.btn_View);

        db = new DBHelper(this);

        inser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeTXT= nome.getText().toString();
                String nascimentoTXT= nascimento.getText().toString();
                String enderecoTXT= endereço.getText().toString();
                String telefoneTXT= telefone.getText().toString();
                String rgTXT= rg.getText().toString();
                String cpfTXT= cpf.getText().toString();
                String emailTXT= email.getText().toString();
                String usuarioTXT= usuario.getText().toString();
                String senhaTXT= senha.getText().toString();
                String generoTXT= genero.getText().toString();
                String instrumentoTXT= instrumento.getText().toString();

                boolean checkinsertdata= db.insertuserdata(nomeTXT,nascimentoTXT,enderecoTXT,telefoneTXT,rgTXT,cpfTXT,emailTXT,usuarioTXT,senhaTXT,generoTXT,instrumentoTXT);
                if(checkinsertdata==true)

                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Inserted",Toast.LENGTH_LONG).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeTXT= nome.getText().toString();
                String nascimentoTXT= nascimento.getText().toString();
                String enderecoTXT= endereço.getText().toString();
                String telefoneTXT= telefone.getText().toString();
                String rgTXT= rg.getText().toString();
                String cpfTXT= cpf.getText().toString();
                String emailTXT= email.getText().toString();
                String usuarioTXT= usuario.getText().toString();
                String senhaTXT= senha.getText().toString();
                String generoTXT= genero.getText().toString();
                String instrumentoTXT= instrumento.getText().toString();

                boolean checkupdatedata= db.updateuserdata(nomeTXT,nascimentoTXT,enderecoTXT,telefoneTXT,rgTXT,cpfTXT,emailTXT,usuarioTXT,senhaTXT,generoTXT,instrumentoTXT);
                if(checkupdatedata==true)

                    Toast.makeText(MainActivity.this,"Entry Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Update",Toast.LENGTH_LONG).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeTXT= nome.getText().toString();


                boolean checkdeletedata= db.deletedata(nomeTXT);
                if(checkdeletedata==true)

                    Toast.makeText(MainActivity.this,"Entry Delete",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Entry Not Delete",Toast.LENGTH_LONG).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"Entry Exists",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Nome:"+res.getString(0)+"\n");
                    buffer.append("nascimento:"+res.getString(1)+"\n");
                    buffer.append("endereco:"+res.getString(2)+"\n");
                    buffer.append("telefone:"+res.getString(3)+"\n");
                    buffer.append("rg:"+res.getString(4)+"\n");
                    buffer.append("cpf:"+res.getString(5)+"\n");
                    buffer.append("email:"+res.getString(6)+"\n");
                    buffer.append("usuario:"+res.getString(7)+"\n");
                    buffer.append("senha:"+res.getString(8)+"\n");
                    buffer.append("genero:"+res.getString(9)+"\n");
                    buffer.append("instrumento:"+res.getString(10)+"\n");
                }

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Users Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}