package com.simple.commtree;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dhru1 on 9/12/2016.
 */
public class EditDialog extends Dialog implements android.view.View.OnClickListener {
    /**
     * This method will be invoked when a button in the dialog is clicked.
     *
     * @param dialog The dialog that received the click.
     * @param which  The button that was clicked (e.g.
     *               {@link DialogInterface#BUTTON1}) or the position
     */
    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p/>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     * @see android.R.styleable#Theme_dialogTheme
     */
    Context context;

    EditText pName, pEmail, pPhone, pAge;
    Button bOK,bCancel;

    public EditDialog(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_update_person);

        bOK = (Button) findViewById(R.id.bOk);
        bCancel = (Button) findViewById(R.id.bCancel);
        pName = (EditText) findViewById(R.id.pName);
        pEmail = (EditText) findViewById(R.id.pEmail);
//              pPhone = (EditText) findViewById(R.id.pPhone);
        pAge = (EditText) findViewById(R.id.pAge);
        bOK.setOnClickListener(this);
    }

    @Override
    public void onClick( View which) {

    }
}
