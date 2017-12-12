package com.example.adam.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    //    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        mQuestionTextView = findViewById(R.id.question_text_view);
//        int question = mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextView.setText(question);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNextQuestion();
            }
        });

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
//                Toast toast = Toast.makeText(
//                    QuizActivity.this,
//                    R.string.correct_toast,
//                    Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP, 0, 0);
//                toast.show();
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
//                Toast toast = Toast.makeText(
//                    QuizActivity.this,
//                    R.string.incorrect_toast,
//                        Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP,0,0);
//                toast.show();
            }
        });
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                int question = mQuestionBank[mCurrentIndex].getTextResId();
//                mQuestionTextView.setText(question);
//                incrementQuestionIdx();
//                updateQuestion();
                displayNextQuestion();
            }
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrevQuestion();
            }
        });

        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Log.d(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void incrementQuestionIdx() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
    }

    private void displayNextQuestion() {
        incrementQuestionIdx();
        updateQuestion();
    }

    private void displayPrevQuestion() {
        mCurrentIndex = (mCurrentIndex + mQuestionBank.length - 1) % mQuestionBank.length;
        updateQuestion();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
//        incrementQuestionIdx();
//        updateQuestion();
        displayNextQuestion();
    }
}
