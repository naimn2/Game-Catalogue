package com.muflihun.core.utils

import com.muflihun.core.data.source.local.entity.GameEntity
import com.muflihun.core.data.source.remote.response.ResultsItem
import com.muflihun.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<GameEntity> =
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
    )
}