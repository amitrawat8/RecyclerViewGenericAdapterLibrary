package com.rvlibrary.ui

import com.amit.rvadapter.ViewType
import com.rvlibrary.R

import com.rvlibrary.data.model.Answer
import com.rvlibrary.data.model.DataItem
import com.rvlibrary.data.model.SingleChoiceQuestion
//viewtype and model
data class SingleChoiceViewType(private val model: SingleChoiceQuestion) :
    ViewType<SingleChoiceQuestion> {

    override fun layoutId(): Int = R.layout.layout_single_choice

    override fun data(): SingleChoiceQuestion = model
}

class CarDescriptionViewType : ViewType<Unit> {

    override fun layoutId(): Int = R.layout.layout_car_description

    override fun data() {}
}

data class QuestionViewType(private val model: String) : ViewType<String> {

    override fun layoutId(): Int = R.layout.layout_multiple_choice

    override fun data(): String = model
}

data class MultipleAnswerViewType(private val model: Answer) : ViewType<Answer> {

    override fun layoutId(): Int = R.layout.layout_answer

    override fun data(): Answer = model
}

data class DataItemListViewType(private val model: DataItem) : ViewType<DataItem> {
    override fun layoutId(): Int = R.layout.layout_data_list;

    override fun data(): DataItem = model;

}