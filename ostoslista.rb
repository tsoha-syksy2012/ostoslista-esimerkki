# encoding: utf-8
require 'sinatra/base'
require 'sinatra/reloader'
require './config/sequel'

class Ostoslista < Sinatra::Base
  configure :development do
    register Sinatra::Reloader
  end

  configure do
    enable :sessions
  end

  helpers do
    def kirjautunut?
      !session[:kayttaja].nil?
    end

    def kirjautunut_kayttaja
      DB.fetch("SELECT id, username FROM users WHERE id = ?", session[:kayttaja]).first
    end
  end

  get '/' do
    unless kirjautunut?
      redirect '/kirjautuminen'
    else
      lista = DB.fetch("SELECT * FROM lists WHERE user_id = ? AND is_default = true", kirjautunut_kayttaja[:id]).first
      redirect "/lista/#{lista[:id]}"
    end
  end

  get '/lista/:id' do
      lista = DB.fetch("SELECT * FROM lists WHERE user_id = ? AND id = ?", kirjautunut_kayttaja[:id], params[:id]).first
      if lista
        erb :lista, locals: {otsikko: "Ostoslista - #{lista[:name]}", kayttaja: kirjautunut_kayttaja, lista: lista}
      else
        halt(404)
      end
  end

  get '/kirjautuminen' do
    erb :kirjautumislomake, locals: {otsikko: 'Ostoslista - kirjautuminen'}
  end

  post '/kirjautuminen' do
    kayttaja = DB.fetch("SELECT id FROM users WHERE username = ? AND password = ?", params[:tunnus], params[:salasana]).first
    if kayttaja
      session[:kayttaja] = kayttaja[:id]
      redirect '/'
    else
      erb :kirjautumislomake, locals: {otsikko: 'Ostoslista - kirjautuminen', virhe: 'Kirjautuminen epäonnistui. Tarkista tunnus tai salasana!'}
    end
  end

  not_found do
    erb :virhe404, locals: {otsikko: 'Sivua ei löydy'}
  end

end
