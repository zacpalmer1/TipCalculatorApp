package edu.cofc.andriod.tipcalculator2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import edu.cofc.andriod.tipcalculator2.databinding.ActivityMainBinding
import java.lang.Math.ceil
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Force Keyboard and EditText to open with App
        val editText = findViewById<EditText>(R.id.amount)
        editText.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        // Set onClickListener
        binding.optionThirtyPercent.setOnClickListener {
            calculateTip()
        }
        binding.optionTwentyfivePercent.setOnClickListener {
            calculateTip()
        }
        binding.optionTwentyPercent.setOnClickListener {
            calculateTip()
        }
        binding.optionFifthteenPercent.setOnClickListener {
            calculateTip()
        }
        binding.tipOptions.setOnClickListener {
            calculateTip()
        }
        binding.roundTip.setOnClickListener {
            calculateTip()
        }
        binding.calculateBtn.setOnClickListener {
            calculateTip()
        }
        // Change background color
        binding.root.setBackgroundColor(Color.parseColor("#E4DEE4"))
    }

    private fun calculateTip() {
        val cost = binding.amount.text.toString().toDouble()
        val tipPercentatge = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_fifthteen_percent -> 0.15
            R.id.option_twenty_percent -> 0.20
            R.id.option_twentyfive_percent -> 0.25
            else -> 0.30
        }
        var tip = cost * tipPercentatge
        val roundUp = binding.roundTip.isChecked

        if (roundUp) {
            tip = ceil(tip)
        }
        val currencyTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, currencyTip)
        val totalAmount = NumberFormat.getCurrencyInstance().format(tip + cost)
        binding.totalAmount.text = getString(R.string.total_amount, totalAmount)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TIP_KEY", binding.tipAmount.text.toString())
        outState.putInt("SELECTED_OPTION_KEY", binding.tipOptions.checkedRadioButtonId)
        outState.putString("TOTAL_KEY", binding.totalAmount.text.toString())
        outState.putInt("SELECTED_OPTION_KEY", binding.tipOptions.checkedRadioButtonId)

    }


    override fun onRestoreInstanceState(inState: Bundle) {
        super.onRestoreInstanceState(inState)
        binding.tipAmount.text = inState.getString("TIP_KEY")
        binding.tipOptions.check(inState.getInt("SELECTED_OPTION_KEY"))
        binding.totalAmount.text = inState.getString("TOTAL_KEY")
        binding.tipOptions.check(inState.getInt("SELECTED_OPTION_KEY"))

    }
}


