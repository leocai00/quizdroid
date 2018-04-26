package edu.washington.wanxic.quizdroid

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import edu.washington.wanxic.quizdroid.Fragments.AnswerFragment
import edu.washington.wanxic.quizdroid.Fragments.QuizFragment
import edu.washington.wanxic.quizdroid.Fragments.TopicFragment

class SecondActivity : AppCompatActivity(),
        TopicFragment.OnFragmentInteractionListener,
        QuizFragment.OnFragmentInteractionListener,
        AnswerFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val topic = intent.getStringExtra("keyIdentifier")
        var  topicPage : TopicFragment = TopicFragment.newInstance(topic)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, topicPage)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.i("SecondActivity", "start")
    }
}
