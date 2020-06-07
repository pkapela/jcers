#!/usr/bin/env bash

echo "Provisioning Virtual Machine..."

echo "Preparing MySQL"
apt-get update > /dev/null
apt-get install debconf-utils -y > /dev/null
debconf-set-selections <<< 'mysql-server mysql-server/root_password password vagrant'
debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password vagrant'

echo "Installing MySQL"
apt-get install mysql-server -y > /dev/null