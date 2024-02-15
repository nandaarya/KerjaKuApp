package com.example.kerjakuapp.ui.reviewreimbursement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kerjakuapp.databinding.ActivityReviewReimbursementBinding

class ReviewReimbursementActivity : AppCompatActivity() {

    lateinit var binding: ActivityReviewReimbursementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewReimbursementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Review Reimbursement"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}