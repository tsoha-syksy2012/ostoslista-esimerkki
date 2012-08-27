require 'sinatra/base'
require 'sinatra/reloader'

class Ostoslista < Sinatra::Base
  configure :development do
    register Sinatra::Reloader
  end
end
