package com.joelkanyi.mealtime.api.mealtimeapi.favorite.controller

import com.joelkanyi.mealtime.api.mealtimeapi.favorite.data.dto.CreateFavoriteDto
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.model.Favorite
import com.joelkanyi.mealtime.api.mealtimeapi.favorite.service.FavoriteService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/favorite")
class FavoriteController(
    private val favoriteService: FavoriteService
) {
    @GetMapping
    fun getFavorites(
        @PathVariable userId: String
    ): List<Favorite> {
        return favoriteService.retrieveFavorites(
            userId = userId
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addFavorite(
        @RequestBody favorite: CreateFavoriteDto,
        @PathVariable userId: String
    ): String {
        return favoriteService.addFavorite(
            favorite = favorite,
            userId = userId
        )
    }

    @DeleteMapping("/{favoriteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteFavorite(
        @PathVariable favoriteId: Int,
        @PathVariable userId: String
    ) {
        favoriteService.deleteFavorite(
            favoriteId = favoriteId,
            userId = userId
        )
    }
}