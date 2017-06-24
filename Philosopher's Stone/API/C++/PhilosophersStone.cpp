#include "PhilosophersStone.h"

PhilosophersStone::PhilosophersStone() {

}

PhilosophersStone::~PhilosophersStone() {

}

void PhilosophersStone::tag(std::string tag) {

	if(!isTagged(tag))
		tags.push_back(tag);
}

void PhilosophersStone::removeTag(std::string tag) {

	for(unsigned int i = 0; i < tags.size(); i++) {

		if(tags.at(i).compare(tag) == 0) {

			tags.erase(tags.begin() + i);

			return;
		}
	}
}

std::vector<std::string> PhilosophersStone::getTags() {
	return tags;
}

bool PhilosophersStone::isTagged(std::string tag) {

	for(unsigned int i = 0; i < tags.size(); i++) {

		if(tags.at(i).compare(tag) == 0)
			return true;
	}

	return false;
}

void PhilosophersStone::publiclyConnect(PhilosophersStone* connection) {

	if(isPubliclyConnected(connection))
		return;

	publicConnections.push_back(connection);
}

void PhilosophersStone::privatelyConnect(PhilosophersStone* connection) {

	if(isPrivatelyConnected(connection))
		return;

	privateConnections.push_back(connection);
}

void PhilosophersStone::publiclyConnectMutually(PhilosophersStone* connection) {
	publiclyConnect(connection);
	connection->publiclyConnect(this);
}

void PhilosophersStone::privatelyConnectMutually(PhilosophersStone* connection) {
	privatelyConnect(connection);
	connection->privatelyConnect(this);
}

void PhilosophersStone::disconnect(PhilosophersStone* connection) {

	int index = getIndexOfPublicConnection(connection);

	if(index != -1) {

		publicConnections.erase(publicConnections.begin() + index);

		return;
	}

	index = getIndexOfPrivateConnection(connection);

	if(index != -1) {

		privateConnections.erase(privateConnections.begin() + index);

		return;
	}
}

void PhilosophersStone::disconnectMutually(PhilosophersStone* connection) {
	disconnect(connection);
	connection->disconnect(this);
}

bool PhilosophersStone::isConnected(PhilosophersStone* connection) {
	return isPubliclyConnected(connection) || isPrivatelyConnected(connection);
}

bool PhilosophersStone::isPubliclyConnected(PhilosophersStone* connection) {

	for(unsigned int i = 0; i < publicConnections.size(); i++) {

		if(publicConnections.at(i) == connection)
			return true;
	}

	return false;
}

bool PhilosophersStone::isPrivatelyConnected(PhilosophersStone* connection) {

	for(unsigned int i = 0; i < privateConnections.size(); i++) {

		if(privateConnections.at(i) == connection)
			return true;
	}

	return false;
}

std::vector<PhilosophersStone*> PhilosophersStone::getConnections() {

	std::vector<PhilosophersStone*> connections;

	connections.resize(publicConnections.size() + privateConnections.size());

	for(unsigned int i = 0; i < publicConnections.size(); i++)
		connections.push_back(publicConnections.at(i));

	for(unsigned int i = 0; i < privateConnections.size(); i++)
		connections.push_back(privateConnections.at(i));

	return connections;
}

std::vector<PhilosophersStone*> PhilosophersStone::getPublicConnections() {
	return publicConnections;
}

std::vector<PhilosophersStone*> PhilosophersStone::getPrivateConnections() {
	return privateConnections;
}

std::vector<PhilosophersStone*> PhilosophersStone::get(std::vector<std::string> tags) {

	std::vector<PhilosophersStone*> path;
	std::vector<PhilosophersStone*> stones;

	getTraverse(true, tags, path, stones);

	return stones;
}

std::vector<PhilosophersStone*> PhilosophersStone::getAtlas() {

	std::vector<PhilosophersStone*> path;
	std::vector<PhilosophersStone*> stones;

	getAtlasTraverse(true, path, stones);

	return stones;
}

bool PhilosophersStone::has(std::vector<std::string> tags) {

	std::vector<PhilosophersStone*> path;

	return hasTraverse(true, tags, path);
}

std::vector<void*> PhilosophersStone::call(std::vector<void*> packet) {

	std::vector<PhilosophersStone*> path;
	std::vector<void*> calls;

	callTraverse(true, packet, path, calls);

	return calls;
}

void* PhilosophersStone::onCall(std::vector<void*> packet) {
	return NULL;
}

int PhilosophersStone::getIndexOfPublicConnection(PhilosophersStone* connection) {

	for(unsigned int i = 0; i < publicConnections.size(); i++) {

		if(publicConnections.at(i) == connection)
			return i;
	}

	return -1;
}

int PhilosophersStone::getIndexOfPrivateConnection(PhilosophersStone* connection) {

	for(unsigned int i = 0; i < privateConnections.size(); i++) {

		if(privateConnections.at(i) == connection)
			return i;
	}

	return -1;
}

void PhilosophersStone::callTraverse(
		bool init,
		std::vector<void*> packet,
		std::vector<PhilosophersStone*> path,
		std::vector<void*> calls) {

	if(traversed(path))
		return;

	path.push_back(this);

	void* call = NULL;

	try {
		call = onCall(packet);
	}

	catch(...) {

	}

	if(call != NULL)
		calls.push_back(call);

	if(init) {

		for(unsigned int i = 0; i < privateConnections.size(); i++)
			privateConnections.at(i)->callTraverse(false, packet, path, calls);
	}

	for(unsigned int i = 0; i < publicConnections.size(); i++)
		publicConnections.at(i)->callTraverse(false, packet, path, calls);
}

void PhilosophersStone::getTraverse(
		bool init,
		std::vector<std::string> tags,
		std::vector<PhilosophersStone*> path,
		std::vector<PhilosophersStone*> stones) {

	if(traversed(path))
		return;

	path.push_back(this);

	if(hasAllTags(tags))
		stones.push_back(this);

	if(init) {

		for(unsigned int i = 0; i < privateConnections.size(); i++)
			privateConnections.at(i)->getTraverse(false, tags, path, stones);
	}

	for(unsigned int i = 0; i < publicConnections.size(); i++)
		publicConnections.at(i)->getTraverse(false, tags, path, stones);
}

void PhilosophersStone::getAtlasTraverse(
		bool init,
		std::vector<PhilosophersStone*> path,
		std::vector<PhilosophersStone*> stones) {

	if(traversed(path))
		return;

	path.push_back(this);
	stones.push_back(this);

	if(init) {

		for(unsigned int i = 0; i < privateConnections.size(); i++)
			privateConnections.at(i)->getTraverse(false, tags, path, stones);
	}

	for(unsigned int i = 0; i < publicConnections.size(); i++)
		publicConnections.at(i)->getTraverse(false, tags, path, stones);
}

bool PhilosophersStone::hasTraverse(
		bool init,
		std::vector<std::string> tags,
		std::vector<PhilosophersStone*> path) {

	if(traversed(path))
		return false;

	path.push_back(this);

	if(hasAllTags(tags))
		return true;

	if(init) {

		for(unsigned int i = 0; i < privateConnections.size(); i++) {

			if(privateConnections.at(i)->hasTraverse(false, tags, path))
				return true;
		}
	}

	for(unsigned int i = 0; i < publicConnections.size(); i++) {

		if(publicConnections.at(i)->hasTraverse(false, tags, path))
			return true;
	}

	return false;
}

bool PhilosophersStone::traversed(std::vector<PhilosophersStone*> path) {

	for(unsigned int i = 0; i < path.size(); i++) {

		if(path.at(i) == this)
			return true;
	}

	return false;
}

bool PhilosophersStone::hasAllTags(std::vector<std::string> tags) {

	for(unsigned int i = 0; i < tags.size(); i++) {

		if(!isTagged(tags.at(i)))
			return false;
	}

	return true;
}
