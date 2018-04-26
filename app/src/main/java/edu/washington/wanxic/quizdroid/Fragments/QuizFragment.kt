package edu.washington.wanxic.quizdroid.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import edu.washington.wanxic.quizdroid.Question

import edu.washington.wanxic.quizdroid.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuizFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String = ""
    private var param2: IntArray = IntArray(2, {0})

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getIntArray(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_quiz, container, false)
        val textView = view.findViewById<TextView>(R.id.question)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val answer1 = view.findViewById<RadioButton>(R.id.radioButton1)
        val answer2 = view.findViewById<RadioButton>(R.id.radioButton2)
        val answer3 = view.findViewById<RadioButton>(R.id.radioButton3)
        val answer4 = view.findViewById<RadioButton>(R.id.radioButton4)
        val submit = view.findViewById<Button>(R.id.submit)
        submit.isClickable = false

        var question : Question
        if(param1 == "Math") {
            when(param2[0]) {
                0 -> question = Question("1 + 1 = ?", arrayOf("1", "2", "3", "4"), 1)
                1 -> question = Question("1 + 2 = ?", arrayOf("1", "2", "3", "4"), 2)
                else -> question = Question("1 + 3 = ?", arrayOf("1", "2", "3", "4"), 3)
            }
        } else if (param1 == "Physics") {
            when(param2[0]) {
                0 -> question = Question("If an object is in motion, what kind of energy does it apply?",
                        arrayOf("Potential Energy", "Caloric Energy", "Kinetic Energy", "Metabolic Energy"), 2)
                1 -> question = Question("What is the force holds back a sliding object?",
                        arrayOf("Friction", "Gravity", "Momentum", "Deceleration"), 0)
                else -> question = Question("Force is measured by what unit of measure?",
                        arrayOf("Joules", "Degrees", "Newtons", "Calories"), 2)
            }
        } else {
            when(param2[0]) {
                0 -> question = Question("Which Super Hero Team Does Johnny Storm Belong To?",
                        arrayOf("Ultimate Avengers", "The Justice League", "The X-Men", "The Fantastic 4"), 3)
                1 -> question = Question("What Is Anthony Stark's Super Hero Name?",
                        arrayOf("Spider Man", "Iron Man", "Deadpool", "Wolverine"), 1)
                else -> question = Question("How old is Thor?",
                        arrayOf("25 years old", "100 years old", "500 years old", "At lease 1000 years old"), 3)
            }
        }

        implement(question, textView, answer1, answer2, answer3, answer4)

        // Toast.makeText(this, numOfCorrect.toString(), Toast.LENGTH_SHORT).show()
        radioGroup.setOnCheckedChangeListener{group, CheckedId ->
            submit.isClickable = true

            var currentAnswer = view.findViewById<RadioButton>(CheckedId).text
            param2[0] += 1
            //Log.i("Quiz", math1.options[math1.answer])
            if(currentAnswer == question.options[question.answer]) {
                param2[1] += 1
            }

            val  answerPage : AnswerFragment = AnswerFragment.newInstance(param1, param2, currentAnswer.toString(), question)
            submit.setOnClickListener {
                this.fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.container, answerPage)
                        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
            }
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: IntArray) =
                QuizFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putIntArray(ARG_PARAM2, param2)
                    }
                }
    }

    private fun implement(question: Question, textView: TextView, answer1: RadioButton,
                          answer2: RadioButton, answer3: RadioButton, answer4: RadioButton): Unit {
        textView.text = question.question
        answer1.text = question.options[0]
        answer2.text = question.options[1]
        answer3.text = question.options[2]
        answer4.text = question.options[3]
    }
}
