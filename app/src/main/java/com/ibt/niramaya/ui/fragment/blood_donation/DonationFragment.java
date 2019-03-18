package com.ibt.niramaya.ui.fragment.blood_donation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseFragment;

public class DonationFragment extends BaseFragment {
    private View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_donation, container, false);
        init();
        return rootview;
    }

    private void init() {
    }

    private void openDonorDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setCancelable(false);

    }

    /*private fun showDialog(studentIds: String) {
        val dialogBuilder = AlertDialog.Builder(mContext)
        //dialogBuilder.tit
        dialogBuilder.setCancelable(false)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_team, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        val btnCreate = dialogView.findViewById<Button>(R.id.btnDialogCreate)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnDialogCancel)
        val etTeamName = dialogView.findViewById<EditText>(R.id.etAddTeam)
        val spnBatch = dialogView.findViewById<Spinner>(R.id.spnBatch)
        //msgBox.filters = InputFilter[](InputFilter())


        val batchAdapter = ArrayAdapter(mContext, R.layout.spinner_item_team, batchArray)
        spnBatch.adapter = batchAdapter
        spnBatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position > 0) {
                    sBatch = batchArray[position]
                }else{
                    sBatch = ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        btnCancel.setOnClickListener { alertDialog.dismiss() }
        btnCreate.setOnClickListener {
            val teamName =  etTeamName.text.toString().trim()
            if (teamName.isEmpty()){
                showToast(mContext, "Write a team name!")
            }else if (sBatch.isEmpty()){
                showToast(mContext, "No Batch selected!")
            }else{
                createTeam(teamName, studentIds)
            }
            alertDialog.dismiss()
        }
        alertDialog.show()
    }*/
}
