package com.example.myapp.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapp.domain.model.Article

@Composable
fun NewsContentScreen(
    article: Article,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "New's Image"
            )
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = article.content ?: "No description available",
                style = MaterialTheme.typography.bodySmall
            )
            Button(
                onClick = {
                    Log.d("my Button", "Where am I going?")
                    navController.navigate("news_list")
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text("Voltar")
            }
        }
    }
}