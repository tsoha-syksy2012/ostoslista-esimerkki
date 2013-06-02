module Handler.List (
  getListR,
  getListsR,
  postNewListR,
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
