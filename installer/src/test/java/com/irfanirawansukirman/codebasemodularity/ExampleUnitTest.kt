package com.irfanirawansukirman.codebasemodularity

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//data class User(val id: Int)
//
//class MainViewModel : ViewModel() {
//    private val _user = MutableLiveData<User>()
//    val user: LiveData<User>
//        get() = _user
//
//    fun fetchUser(id: Int) {
//        val user = User(id)
//
//        _user.value = user
//    }
//}
//
//inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
//
//class ExampleUnitTest {
//
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//    private lateinit var viewModel: MainViewModel
//
//    private val observer: Observer<User> = mock()
//
//    @Before
//    fun setupBefore() {
//        viewModel = MainViewModel()
//        viewModel.user.observeForever(observer)
//    }
//
//    @Test
//    fun `fetch user should return user`() {
//        val expectedUser = User(1)
//
//        viewModel.fetchUser(expectedUser.id)
//
//        val captor = ArgumentCaptor.forClass(User::class.java)
//        captor.run {
//            verify(observer, times(1)).onChanged(capture())
//            assertEquals(expectedUser, value.id)
//        }
//    }
//}
