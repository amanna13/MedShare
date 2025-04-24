package com.example.medshare.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medshare.Dto.SearchResultsItem
import com.example.medshare.RetrofitInterface.MedicineService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchResults @Inject constructor(private val medicineService: MedicineService) : ViewModel() {

    private val _searchResult = MutableStateFlow<ArrayList<SearchResultsItem>>(arrayListOf())
    val searchResult: StateFlow<ArrayList<SearchResultsItem>> = _searchResult.asStateFlow()

    fun  searchResults(tags: List<String>) {
        viewModelScope.launch {
            try {
                println("Searching for $tags")
                val response = medicineService.searchMedicine(tags)
                println(response.body())
                if(response.isSuccessful){
                    println(response.body())
                    println(response.code())
                    _searchResult.update { response.body()!!}
                    println("Here is the result " + searchResult)
                }
            }catch (e : Exception){
                println(e.message)
                println("Crashed")
            }
        }

    }
}