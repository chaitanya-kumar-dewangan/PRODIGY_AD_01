package com.example.calculator

import android.content.Context as RhinoContext
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
class CalculatorViewModel: ViewModel() {
    private val _equationText= MutableLiveData("")
    val equationtext : LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText : LiveData<String> = _resultText

    fun onButtonClick(btn:String){
        Log.i("Clicked Button", btn)
        equationtext.value?.let {
            if (btn=="AC"){
                _equationText.value =""
                _resultText.value ="0"
                return
            }
            if(btn=="C"){
                if(it.isNotEmpty()){
                    _equationText.value=it.substring(0,it.length-1)

                }
                return
            }
            if(btn=="="){
                _equationText.value=resultText.value
                return
            }
            _equationText.value = it+btn
            //calculate result
            try {
                _resultText.value = calculateResult(_equationText.value.toString())
            }catch (_:Exception){


            }            }
        }

    }

private fun calculateResult(equation: String): String {
    val rhinoContext = org.mozilla.javascript.Context.enter()
    rhinoContext.optimizationLevel = -1
    val scriptable: Scriptable = rhinoContext.initStandardObjects()
    var finalResult = rhinoContext.evaluateString(scriptable, equation, "JavaScript", 1, null).toString()
    org.mozilla.javascript.Context.exit()  // Always exit the context to avoid memory leaks
    if(finalResult.endsWith(".0")){
        finalResult=finalResult.replace(".0", "")
    }
    return finalResult
}