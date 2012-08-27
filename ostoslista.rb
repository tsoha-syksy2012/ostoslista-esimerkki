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

  before do
    unless kirjautunut? or request.path_info =~ /^\/kirjautuminen/
      redirect '/kirjautuminen'
    end
  end

  get '/' do
    lista = DB.fetch("SELECT * FROM lists WHERE user_id = ? AND is_default = true", kirjautunut_kayttaja[:id]).first
    redirect "/lista/#{lista[:id]}"
  end

  get '/lista/:id' do
      lista = DB.fetch("SELECT * FROM lists WHERE user_id = ? AND id = ?", kirjautunut_kayttaja[:id], params[:id]).first
      if lista
        tuotteet = DB.fetch("SELECT * FROM items WHERE list_id = ?", lista[:id]).all
        erb :lista, locals: {otsikko: "Ostoslista - #{lista[:name]}", kayttaja: kirjautunut_kayttaja, lista: lista, tuotteet: tuotteet}
      else
        halt(404)
      end
  end

  get '/listat' do
    listat = DB.fetch("SELECT * FROM lists WHERE user_id = ?", kirjautunut_kayttaja[:id]).all
    erb :listat, locals: {otsikko: 'Ostoslista - Kaikki ostoslistat', listat: listat}
  end

  post '/uusilista' do
    id = DB[:lists].insert(user_id: kirjautunut_kayttaja[:id], name: "Ostoslista (#{Date.today})")
    redirect "/lista/#{id}"
  end

  get '/poistalistalta/:tuote' do
    tuote = DB[:items].where(id: params[:tuote])
    lista_id = tuote.first[:list_id]
    tuote.delete
    redirect "/lista/#{lista_id}"
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

  get '/ulos' do
    session[:kayttaja] = nil
    redirect '/kirjautuminen'
  end

  not_found do
    erb :virhe404, locals: {otsikko: 'Sivua ei löydy'}
  end

end
