module Handler.Root where

import Import

getRootR :: Handler ()
getRootR = do
  redirect HomeR
