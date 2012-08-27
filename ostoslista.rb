# encoding: utf-8
require 'sinatra/base'
require 'sinatra/reloader'

class Ostoslista < Sinatra::Base
  configure :development do
    register Sinatra::Reloader
  end

  get '/kirjautuminen' do
    erb :kirjautumislomake, locals: {otsikko: 'Ostoslista - kirjautuminen'}
  end

  post '/kirjautuminen' do
    "lomake lähetetty"
  end

end
