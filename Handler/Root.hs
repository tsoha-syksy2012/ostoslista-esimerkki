module Handler.Root (
  getRootR
) where

import Import

getRootR :: Handler ()
getRootR = do
  userId <- requireAuthId
  lists <- runDB $ selectList [ListUserId ==. userId, ListIsDefault ==. True] []
  let ((Entity lid _):_) = lists
  redirect $ ListR lid
