#ifndef PHILOSOPHER_S_STONE_PHILOSOPHERSSTONE_H_
#define PHILOSOPHER_S_STONE_PHILOSOPHERSSTONE_H_

#include <iostream>
#include <vector>

class PhilosophersStone {

public:

	PhilosophersStone();
	~PhilosophersStone();

	void tag(std::string tag);
	void removeTag(std::string tag);

	std::vector<std::string> getTags();

	bool isTagged(std::string tags);

	void publiclyConnect(PhilosophersStone* connection);
	void privatelyConnect(PhilosophersStone* connection);

	void publiclyConnectMutually(PhilosophersStone* connection);
	void privatelyConnectMutually(PhilosophersStone* connection);

	void disconnect(PhilosophersStone* connection);
	void disconnectMutually(PhilosophersStone* connection);

	bool isConnected(PhilosophersStone* connection);

	bool isPubliclyConnected(PhilosophersStone* connection);
	bool isPrivatelyConnected(PhilosophersStone* connection);

	std::vector<PhilosophersStone*> getConnections();

	std::vector<PhilosophersStone*> getPublicConnections();
	std::vector<PhilosophersStone*> getPrivateConnections();

	std::vector<PhilosophersStone*> get(std::vector<std::string> tags);
	std::vector<PhilosophersStone*> getAtlas();

	bool has(std::vector<std::string> tags);

	std::vector<void*> call(std::vector<void*> packet);

	void* onCall(std::vector<void*> packet);

private:

	int getIndexOfPublicConnection(PhilosophersStone* connection);
	int getIndexOfPrivateConnection(PhilosophersStone* connection);

	void callTraverse(
		bool init,
		std::vector<void*> packet,
		std::vector<PhilosophersStone*> path,
		std::vector<void*> calls);

	void getTraverse(
		bool init,
		std::vector<std::string> tags,
		std::vector<PhilosophersStone*> path,
		std::vector<PhilosophersStone*> vector);

	void getAtlasTraverse(
		bool init,
		std::vector<PhilosophersStone*> path,
		std::vector<PhilosophersStone*> vector);

	bool hasTraverse(
		bool init,
		std::vector<std::string> tags,
		std::vector<PhilosophersStone*> path);

	bool traversed(std::vector<PhilosophersStone*> path);
	bool hasAllTags(std::vector<std::string> tags);

	std::vector<std::string> tags;

	std::vector<PhilosophersStone*> publicConnections;
	std::vector<PhilosophersStone*> privateConnections;
};

#endif
