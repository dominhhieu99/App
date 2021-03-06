package com.dung.lapit.main.message.detailmessage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dung.lapit.App
import com.dung.lapit.Model.Message
import com.dung.lapit.R
import com.dung.lapit.adapter.DetailMessageAdapter
import com.dung.lapit.main.message.MessageActivity
import com.example.dung.applabit.util.MyUtils
import kotlinx.android.synthetic.main.fragment_mesage.*

class DetailMessageFragment : Fragment(), View.OnClickListener, OnMessageFViewListener {

    private lateinit var detailMessageAdapter: DetailMessageAdapter
    private lateinit var mess: ArrayList<Message>
    private lateinit var messagePresenter: MessagePresenter

    companion object {
        val newFragment: Fragment = DetailMessageFragment()
        val TAG = "DetailMessageFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.getInsatnce().isCheckGetMessage = false
        mess = ArrayList()

        return inflater.inflate(R.layout.fragment_mesage, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun updateMessage(message: Message) {
//
    }

    @SuppressLint("WrongConstant")
    private fun init() {

        (activity as MessageActivity).setSupportActionBar(toolbarMessageD)
        messagePresenter = MessagePresenter(this, (activity as MessageActivity).fUser)
        messagePresenter.getMesssage((activity as MessageActivity).fUser)

        rcvMessageFragment.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rcvMessageFragment.layoutManager = linearLayoutManager
        linearLayoutManager.stackFromEnd = true
        detailMessageAdapter =
                DetailMessageAdapter(
                    activity!!,
                    ArrayList<Message>(),
                    (activity as MessageActivity).fUser.imageAvatarURL!!
                )
        rcvMessageFragment.adapter = detailMessageAdapter

        btnSend.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.btnSend -> {
                val ms = edtSend.text.toString()
                val message: Message = Message(
                    ms,
                    MyUtils().timeHere(),
                    App.getInsatnce().user.idUser,
                    (activity as MessageActivity).fUser.idUser,
                    null,
                    (activity as MessageActivity).fUser.imageAvatarURL
                )
                App.getInsatnce().isMessage = true
                App.getInsatnce().isCheckGetMessage = true
                messagePresenter.senMessage((activity as MessageActivity).fUser, message)
                edtSend.setText("")
            }
        }
    }

    override fun senMessagerSuccess() {
        //TODO...
    }

    override fun senMessagerFailed() {
        //TODO...

    }

    override fun getMessagedSuccess(message: Message) {
        Log.d(TAG, "${message.message}")
        detailMessageAdapter.insertMessage(message)
    }

    override fun getMessagedFailed() {

    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}
