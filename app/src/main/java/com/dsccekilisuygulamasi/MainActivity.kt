package com.dsccekilisuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import com.dsccekilisuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var participantList = mutableListOf<Participant>()
    var winnerParticipant = Participant(fullName = "", dscName = "")
    // mutable = değiştirilebilir liste, öneksiz liste ve immutable liste = değişmez. <> içinde değişken tanımı.
    // lateinit : ben bunu tanımladım ama sonra kullanacağım.
    // iki nokta, nesnenin tipini, = tanımını (init) yapıyor.
    // MainBinding, direkt olarak id ile kodu "binding" yapılmasını sağlıyor.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Bunu çalıştırabilmek için, app/Gradle Scripts/build.gradle (module) 'e aşağıdaki kodu girdik:
        // buildFeatures {
        //        viewBinding true
        //    }
        // bunun yerine bütün tuş takımlarına teker teker atama da yapılabiliyormuş, ama yorucu.
        binding.RollButton.setOnClickListener{rollTheWinner()}
        binding.APButton.setOnClickListener{CheckTheName()}
        // setonclicklistener = basıldığında şunu şunu yap.
    }
    fun addParticipant(){

        var FullNameString = binding.FullNameBlank.text.toString()
        var FullDSCString = binding.DSCNameBlank.text.toString()
        if(TextUtils.isEmpty(FullNameString) || TextUtils.isEmpty(FullDSCString)){
            binding.infoText.text="Please add more details."
        }
        else{
            val participantName = Participant(FullNameString,FullDSCString)
            participantList.add(participantName)
            binding.infoText.text="${participantName.fullName} added to list."
        }
        binding.FullNameBlank.text.clear()
        binding.DSCNameBlank.text.clear()
    }
    fun rollTheWinner(){
        if (participantList.count()<2){
            binding.infoText.text="Please add more participants to roll."
        }
        else{
            winnerParticipant = participantList.random()
            binding.WinnerFullName.text=winnerParticipant.fullName
            binding.WinnerDSCName.text=winnerParticipant.dscName
        }
    }
    fun CheckTheName()
    {

        var fullNameString = binding.FullNameBlank.text.toString()
        var fullDSCString = binding.DSCNameBlank.text.toString()
        var counter =0
        var isItIn=0
        val counter2 = participantList.size
        while (counter != counter2){
            if(fullNameString.lowercase() == participantList[counter].fullName.toString().lowercase() && fullDSCString.lowercase() == participantList[counter].dscName.toString().lowercase()){
                binding.infoText.text="You have already entered this participant before"
                isItIn=1
                break
            }
            counter+=1
        }
        if(isItIn==0 && counter == counter2){
            addParticipant()
        }
    }
}
class Participant(val fullName: String, val dscName : String){

}