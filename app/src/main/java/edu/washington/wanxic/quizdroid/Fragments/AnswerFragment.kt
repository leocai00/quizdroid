package edu.washington.wanxic.quizdroid.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import edu.washington.wanxic.quizdroid.MainActivity
import edu.washington.wanxic.quizdroid.Question

import edu.washington.wanxic.quizdroid.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AnswerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AnswerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AnswerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String = ""
    private var param2: IntArray = IntArray(3)
    private var param3: String? = null
    private var param4: Question? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getIntArray(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getParcelable(ARG_PARAM4)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_answer, container, false)
        val currentAnswerText = view.findViewById<TextView>(R.id.currentAnswer)
        val correctAnswerText = view.findViewById<TextView>(R.id.correctAnswer)
        val ratio =  view.findViewById<TextView>(R.id.ratio)
        val next = view.findViewById<Button>(R.id.next)

        currentAnswerText.text = "Your answer: " + param3
        correctAnswerText.text = "Corrent answer: " + param4!!.options!![param4!!.answer]
        ratio.text = "You have " + param2[1] + " out of 3 corrrent"
        if(param2[0] == 3) next.text = "Finish"

        next.setOnClickListener {
            if(param2[0] < 3) {
                val  quizPage : QuizFragment = QuizFragment.newInstance(param1, param2)
                this.fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.container, quizPage)
                        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.commit()
            } else {
                val intent = Intent(getActivity(), MainActivity::class.java)
                startActivity(intent)
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
         * @return A new instance of fragment AnswerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: IntArray, param3: String, param4: Question) =
                AnswerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putIntArray(ARG_PARAM2, param2)
                        putString(ARG_PARAM3, param3)
                        putParcelable(ARG_PARAM4, param4)
                    }
                }
    }
}
