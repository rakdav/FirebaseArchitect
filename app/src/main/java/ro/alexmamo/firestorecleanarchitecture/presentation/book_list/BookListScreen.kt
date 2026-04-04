package ro.alexmamo.firestorecleanarchitecture.presentation.book_list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.alexmamo.firestorecleanarchitecture.R
import ro.alexmamo.firestorecleanarchitecture.components.LoadingIndicator
import ro.alexmamo.firestorecleanarchitecture.domain.model.Response
import ro.alexmamo.firestorecleanarchitecture.presentation.book_list.components.AddBookFloatingActionButton
import ro.alexmamo.firestorecleanarchitecture.presentation.book_list.components.EmptyBookListContent
import ro.alexmamo.firestorecleanarchitecture.presentation.book_list.components.TopBar

@Composable
fun BookListScreen(
    viewModel: BookListViewModel = viewModel()
) {
    val context = LocalContext.current
    var openAddBookDialog by remember { mutableStateOf(false) }
    val bookListResponse by viewModel.bookListState.collectAsStateWithLifecycle()
    val addBookResponse by viewModel.addBookState.collectAsStateWithLifecycle()
    val updateBookResponse by viewModel.updateBookState.collectAsStateWithLifecycle()
    val deleteBookResponse by viewModel.deleteBookState.collectAsStateWithLifecycle()
    val invalidBookFieldMessage = stringResource(R.string.invalid_book_field_message)
    val bookActionMessage = stringResource(R.string.book_action_message)

    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            AddBookFloatingActionButton (
                onAddFloatingActionButtonClick = {
                    openAddBookDialog=true
                }
            )
        })
        { innerPadding->
            when(val bookListResponse=bookListResponse){
               is Response.Idle->{}
               is Response.Loading-> LoadingIndicator()
               is Response.Success->bookListResponse.data?.let {
                    bookList ->
                   if(bookList.isEmpty()){
                       EmptyBookListContent(innerPadding=innerPadding)
                   }
                   else
                   {

                   }
               }

                else -> {}
            }
        }
}

enum class BookAction(){
    ADD,
    UPDATE,
    DELETE
}

enum class BookField(){
    TITLE,
    AUTHOR
}
