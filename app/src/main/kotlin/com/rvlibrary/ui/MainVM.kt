package com.rvlibrary.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.amit.rvadapter.OnItemActionListener
import com.amit.rvadapter.SingleLiveEvent
import com.amit.rvadapter.ViewType
import com.rvlibrary.R

import com.rvlibrary.data.model.Answer
import com.rvlibrary.data.model.DataItem
import com.rvlibrary.data.model.MultipleChoiceQuestion
import com.rvlibrary.data.model.SingleChoiceQuestion


import com.rvlibrary.utils.ResourceProvider

class MainVM(private val resourceProvider: ResourceProvider) : ViewModel(),
    OnSingleChoiceClickListener<SingleChoiceQuestion>, OnMultipleChoiceClickListener<Answer>,
    OnDataItemListClickListener<DataItem> {

    private val list = ArrayList<ViewType<*>>()

    val insertEvent = SingleLiveEvent<Pair<Int, ViewType<*>>>()

    val removeItemEvent = SingleLiveEvent<Int>()

    val updateEvent = SingleLiveEvent<Pair<Int, ViewType<*>>>()


    //viewtype list adding here
    fun getList(): List<ViewType<*>> {
        list.clear()
        val singleChoiceQuestion = SingleChoiceQuestion(
            question = resourceProvider.getString(R.string.txt_question_car),
            optionOne = resourceProvider.getString(R.string.txt_yes),
            optionTwo = resourceProvider.getString(R.string.txt_no)
        )
        list.add(SingleChoiceViewType(singleChoiceQuestion))

        val multipleChoiceQuestion = MultipleChoiceQuestion(
            question = resourceProvider.getString(R.string.txt_multiple_choice_question),
            optionList = arrayListOf(
                Answer(resourceProvider.getString(R.string.txt_question1)),
                Answer(resourceProvider.getString(R.string.txt_question2)),
                Answer(resourceProvider.getString(R.string.txt_question3))
            )
        )
        list.add(QuestionViewType(multipleChoiceQuestion.question))

        multipleChoiceQuestion.optionList.forEach {
            list.add(MultipleAnswerViewType(it))
        }


        var dataitem = arrayListOf(
            DataItem("Email", R.drawable.ic_baseline_call_24),
            DataItem("Info", R.drawable.ic_baseline_call_24),
            DataItem("Delete", R.drawable.ic_baseline_call_24),
            DataItem("Dialer", R.drawable.ic_baseline_call_24)
        )

        dataitem.forEach {
            list.add(DataItemListViewType(it))
        }
        val multipleChoiceQuestion2 = MultipleChoiceQuestion(
            question = resourceProvider.getString(R.string.txt_multiple_choice_question2),
            optionList = arrayListOf(
                Answer(resourceProvider.getString(R.string.txt_question4)),
                Answer(resourceProvider.getString(R.string.txt_question5)),
                Answer(resourceProvider.getString(R.string.txt_question6))
            )
        )
        list.add(QuestionViewType(multipleChoiceQuestion2.question))
        multipleChoiceQuestion2.optionList.forEach {
            list.add(MultipleAnswerViewType(it))
        }
        return list
    }

    override fun onSingleChoiceOptionClicked(optionId: Int, item: SingleChoiceQuestion) {
        when {
            optionId == 1 && item.isOptionOneSelected == false -> {
                item.isOptionOneSelected = true
                list.add(1, CarDescriptionViewType())
                insertEvent.value = 1 to list[1]
            }
            optionId == 2 && item.isOptionOneSelected == true -> {
                item.isOptionOneSelected = false
                list.removeAt(1)
                removeItemEvent.value = 1
            }
        }
    }

    override fun onMultipleChoiceClicked(optionId: Int, item: Answer) {
        val index = list.indexOf(MultipleAnswerViewType(item))
        list[index] = MultipleAnswerViewType(Answer(item.answer, !item.isSelected))
        updateEvent.value = index to list[index]
    }

    override fun onItemClicked(position: Int) {
    }

    override fun OnDataItemListClickListener(optionId: Int, item: DataItem) {
        val index = list.indexOf(DataItemListViewType(item))
        Log.e("index", "" + index);

    }
}


//onclick listener interface

interface OnSingleChoiceClickListener<T> : OnItemActionListener {
    fun onSingleChoiceOptionClicked(optionId: Int, item: T)
}

interface OnMultipleChoiceClickListener<T> : OnItemActionListener {
    fun onMultipleChoiceClicked(optionId: Int, item: T)
}

interface OnDataItemListClickListener<T> : OnItemActionListener {
    fun OnDataItemListClickListener(optionId: Int, item: T)
}