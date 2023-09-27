package com.example.mybilibili.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybilibili.R

@Composable
@Preview(showBackground = true)
fun MyNavigationBar() {
    data class NavItem(
        val label: String,
        val unselectedIcon: Int,
        val selectedIcon: Int
    )

    val items = listOf(
        NavItem("首页", R.drawable.home_24px, R.drawable.home_24px__1_),
        NavItem("动态", R.drawable.subscriptions_24px, R.drawable.subscriptions_24px__1_),
        NavItem("会员购", R.drawable.local_mall_24px, R.drawable.local_mall_24px__1_),
        NavItem("我的", R.drawable.person_24px, R.drawable.person_24px__1_)
    )

    val navSelected = remember {
        mutableStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, navItem ->
            val selected = (index == navSelected.value)
            NavigationBarItem(selected = selected,
                onClick = { navSelected.value = index },
                icon = {
                    if (selected)
                        Icon(
                            painter = painterResource(navItem.selectedIcon),
                            contentDescription = ""
                        )
                    else
                        Icon(
                            painter = painterResource(id = navItem.unselectedIcon),
                            contentDescription = ""
                        )
                },
                label = { Text(text = navItem.label)}
            )
        }
    }
}