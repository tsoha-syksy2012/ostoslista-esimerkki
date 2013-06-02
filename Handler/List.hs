module Handler.List (
  getListR,
  getListsR,
  postNewListR,
  postAddToListR,
  getRemoveFromListR
) where

import Import

getListR :: ListId -> Handler RepHtml
getListR listId = do
  userId <- requireAuthId
  list <- runDB $ get404 listId
  items <- runDB $ selectList [ItemListId ==. listId] [Asc ItemName]
  defaultLayout $ do
    setTitle $ toHtml $ listName list
    $(widgetFile "list")

getListsR :: Handler RepHtml
getListsR = do
  userId <- requireAuthId
  lists <- runDB $ selectList [ListUserId ==. userId] [Asc ListName]
  defaultLayout $ do
    setTitle "Kaikki ostoslistat"
    $(widgetFile "lists")

postNewListR :: Handler ()
postNewListR = do
  userId <- requireAuthId
  listId <- runDB $ insert $ List userId "Uusi lista" False
  redirect $ ListR listId

postAddToListR :: ListId -> Handler ()
postAddToListR listId = do
  _ <- requireAuthId
  name <- runInputPost $ ireq textField "tuote"
  _ <- runDB $ insert $ Item listId name
  redirect $ ListR listId

getRemoveFromListR :: ItemId -> Handler ()
getRemoveFromListR itemId = do
  _ <- requireAuthId
  item <- runDB $ get404 itemId
  let listId = itemListId item
  runDB $ delete itemId
  redirect $ ListR listId
