package com.muflihun.core.utils

import com.muflihun.core.data.source.local.entity.GameEntity
import com.muflihun.core.data.source.remote.response.GameResponse
import com.muflihun.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> =
        input.map {
            GameEntity(
                gameId = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                slug = it.slug,
                tba = it.tba,
                dominantColor = it.dominantColor,
                saturatedColor = it.saturatedColor,
                playtime = it.playtime,
                ratingTop = it.ratingTop,
                reviewsTextCount = it.reviewsTextCount,
                ratingsCount = it.ratingsCount,
                suggestionsCount = it.suggestionsCount,
                updated = it.updated,
                reviewsCount = it.reviewsCount,
                isFavorite = false,
                genres = it.genres?.joinToString(", ") { genre -> genre?.name.toString() },
                platforms = it.platforms?.joinToString(", ") { platform -> platform?.platform?.name.toString() },
                shortScreenshots = it.shortScreenshots?.joinToString(", ") { screeenshot -> screeenshot?.image.toString() },
                tags = it.tags?.joinToString(", ") { tag -> tag?.name.toString() }
            )
        }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.gameId,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                slug = it.slug,
                tba = it.tba,
                dominantColor = it.dominantColor,
                saturatedColor = it.saturatedColor,
                playtime = it.playtime,
                ratingTop = it.ratingTop,
                reviewsTextCount = it.reviewsTextCount,
                ratingsCount = it.ratingsCount,
                suggestionsCount = it.suggestionsCount,
                updated = it.updated,
                reviewsCount = it.reviewsCount,
                genres = it.genres,
                platforms = it.platforms,
                isFavorite = it.isFavorite,
                shortScreenshots = it.shortScreenshots,
                tags = it.tags,
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.id,
        name = input.name,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        slug = input.slug,
        tba = input.tba,
        dominantColor = input.dominantColor,
        saturatedColor = input.saturatedColor,
        playtime = input.playtime,
        ratingTop = input.ratingTop,
        reviewsTextCount = input.reviewsTextCount,
        ratingsCount = input.ratingsCount,
        suggestionsCount = input.suggestionsCount,
        updated = input.updated,
        reviewsCount = input.reviewsCount,
        genres = input.genres,
        platforms = input.platforms,
        isFavorite = input.isFavorite,
        shortScreenshots = input.shortScreenshots,
        tags = input.tags,
    )
}