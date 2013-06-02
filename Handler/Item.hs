module Handler.Item (
  postAddToListR,
  getRemoveFromListR
) where

import Import

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
