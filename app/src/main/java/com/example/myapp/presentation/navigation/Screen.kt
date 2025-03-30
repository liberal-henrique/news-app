package com.example.myapp.presentation.navigation

sealed class Screen(val route: String) {
    object NewsList : Screen("news_list")
    object NewsDetail : Screen("news_detail/articleId") {
        fun createRoute(articleId: String): String {
            return "news_detail/$articleId"
        }
    }
}